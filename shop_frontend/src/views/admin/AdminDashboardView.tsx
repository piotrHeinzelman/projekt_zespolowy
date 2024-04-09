import React, {type ReactElement, useEffect, useState } from "react"
import ProductList from "../../components/products/ProductList";
import axios from "../../config/axios";
import { API_PRODUCTS_PATH } from "../../constants/api_routes";

const AdminDashboardView = (): ReactElement => {
  const [products, setProducts] = useState([])

  useEffect(() => {
    const fetchProducts = async () => {
      const response = await axios.get(API_PRODUCTS_PATH)
      setProducts(response.data._embedded.products)
    }

    void fetchProducts()
  }, [])

  return (
    <ProductList products={products} admin={true} />
  )
}

export default AdminDashboardView
