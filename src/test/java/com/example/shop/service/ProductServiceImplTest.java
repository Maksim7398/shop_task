package com.example.shop.service;

import com.example.shop.exception.ArticleAlreadyExistsException;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.model.ImmutableUpdateProductRequestBuilder;
import com.example.shop.model.ProductDto;
import com.example.shop.model.ProductDtoBuilder;
import com.example.shop.model.ProductEntityBuilder;
import com.example.shop.persist.entity.ProductEntity;
import com.example.shop.persist.repository.ProductRepository;
import com.example.shop.service.request.ImmutableUpdateProductRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository repositoryMock;
    @Mock
    private ProductMapper productMapperMock;
    @InjectMocks
    private ProductServiceImpl underTest;
    private ProductEntity stubEntity;

    @Test
    public void getProductList_returnProductNotFoundException_test() {
        when(repositoryMock.findAll()).thenReturn(List.of());

        assertThrows(ProductNotFoundException.class, () -> underTest.productList());
    }

    @Test
    public void getProductList_returnExpected_test() {
        stubEntity = ProductEntityBuilder.aProductEntity().build();
        List<ProductEntity> expected = List.of(stubEntity);
        when(repositoryMock.findAll()).thenReturn(expected);
        underTest.productList();

        verify(productMapperMock).listProduct(expected);
        verify(repositoryMock).findAll();
    }

    @Test
    public void getProductById_returnId_test() {
        final UUID expected = UUID.randomUUID();
        ProductEntity expectedProductEntity = ProductEntityBuilder.aProductEntity().build();
        ProductDto expectedProductDto = ProductDtoBuilder.aProductDto().build();

        when(repositoryMock.findById(any())).thenReturn(Optional.of(expectedProductEntity));

        when(productMapperMock.convertFromEntityToDto(any())).thenReturn(expectedProductDto);

        ProductDto actual = underTest.getProductById(expected);
        verify(repositoryMock).findById(expected);


        ArgumentCaptor<ProductEntity> captor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productMapperMock).convertFromEntityToDto(captor.capture());
        ProductEntity captorValue = captor.getValue();
        assertEquals(expectedProductEntity.getTitle(), captorValue.getTitle());

        assertEquals(expectedProductDto.getTitle(), actual.getTitle());
        assertEquals(expectedProductDto.getCreateDate(), actual.getCreateDate());

    }

    @Test
    public void deleteProductById_returnIsEmpty_successfulDelete() {
        final UUID expected = UUID.randomUUID();
        stubEntity = ProductEntityBuilder.aProductEntity().build();

        when(repositoryMock.findById(any())).thenReturn(Optional.of(stubEntity));

        underTest.deleteProductById(expected);

        verify(repositoryMock).deleteById(expected);
    }

    @Test
    public void deleteProductById_returnIsEmpty_throwException() {
        final UUID expected = UUID.randomUUID();

        when(repositoryMock.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.deleteProductById(expected))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("there is no product with this ID");

        verify(repositoryMock).findById(expected);
        verify(repositoryMock, times(0)).deleteById(any());
    }

    @Test
    public void updateProduct_updated() {
        stubEntity = ProductEntityBuilder.aProductEntity().withPrice(new BigDecimal(100)).build();
        ImmutableUpdateProductRequest productRequest = ImmutableUpdateProductRequestBuilder
                .aImmutableUpdateProductRequest().withPrice(new BigDecimal(200)).build();

        when(repositoryMock.findById(stubEntity.getId())).thenReturn(Optional.of(stubEntity));

        underTest.updateProduct(stubEntity.getId(), productRequest);

        ArgumentCaptor<ProductEntity> captor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(repositoryMock).save(captor.capture());
        ProductEntity actual = captor.getValue();
        assertEquals(actual.getPrice(), productRequest.getPrice());
    }

    @Test
    public void updateProduct_checkUniqueArticle_throwArticleAlreadyExists() {
        final UUID expected = UUID.randomUUID();
        stubEntity = ProductEntityBuilder.aProductEntity().withArticle("1").build();
        ImmutableUpdateProductRequest productRequest = ImmutableUpdateProductRequestBuilder
                .aImmutableUpdateProductRequest()
                .withArticle("2")
                .build();

        when(repositoryMock.findById(any())).thenReturn(Optional.of(stubEntity));
        when(repositoryMock.existsByArticle(any())).thenReturn(true);

        assertThatThrownBy(() -> underTest.updateProduct(expected, productRequest))
                .isInstanceOf(ArticleAlreadyExistsException.class)
                .hasMessageContaining("продукт с таким артикулом уже существует");
    }

}