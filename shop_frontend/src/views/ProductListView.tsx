import React, { type ReactElement, useEffect, useState } from 'react'
import { API_PRODUCTS_PATH } from '../constants/api_routes'
import { map } from 'underscore'
import axios from '../config/axios'
import ProductListItem from '../components/products/ProductListItem'
import ProductList from "../components/products/ProductList";

const ProductListView = (): ReactElement => {
  const [products, setProducts] = useState([])

  useEffect(() => {
    const fetchProducts = async () => {
      const response = await axios.get(API_PRODUCTS_PATH)
      setProducts(response.data._embedded.products)
    }

    void fetchProducts()
  }, [])

  return <ProductList products={products} />
}

export default ProductListView;
