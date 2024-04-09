import React, { type ReactElement, useEffect, useState } from "react"
import axios from "../config/axios";
import { API_PRODUCT_PATH } from "../constants/api_routes";
import { useParams } from "react-router-dom";
import { ProductData } from "../constants/types";

const ProductDetailsView = (): ReactElement => {
  const queryParams = useParams()
  const [product, setProduct] = useState({} as ProductData)

  useEffect(() => {
    const fetchProduct = async () => {
      const apiUrl = API_PRODUCT_PATH.replace(':sku', queryParams.sku as string)
      const response = await axios.get(apiUrl)

      setProduct(response.data._embedded.products)
    }

    void fetchProduct()
  }, [])

  return (
    <>{product.name}</>
  )
}

export default ProductDetailsView
