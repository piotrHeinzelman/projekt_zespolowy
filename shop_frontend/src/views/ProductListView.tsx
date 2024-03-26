import React, { type ReactElement, useEffect, useState } from 'react'
import { API_PRODUCTS_PATH } from '../constants/api_routes'
import { map } from 'underscore'
import axios from '../config/axios'
import ProductListItem from '../components/products/ProductListItem'

const ProductListView = (): ReactElement => {
  const [products, setProducts] = useState([])

  useEffect(() => {
    const fetchProducts = async () => {
      // FIXME: Fetch data from API
      // const response = await axios.get(API_PRODUCTS_PATH)
      const response = { data: [{ id: 1, name: 'Product 1' }, { id: 2, name: 'Product 2' }] }
      setProducts(response.data as [])
    }

    void fetchProducts()
  })
  return (
    <div>
      {
        map(products, (product) => {
          return <ProductListItem product={product} />
        })
      }
    </div>
  )
}

export default ProductListView
