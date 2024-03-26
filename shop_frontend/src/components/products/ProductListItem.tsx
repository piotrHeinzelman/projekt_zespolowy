import React, { type ReactElement } from 'react'
import { ProductData } from '../../constants/types'

const ProductListItem = ({ product }: { product: ProductData }): ReactElement => {
  return (
    <div className="flex justify-center items-center">
      <div className="mt-4 p-8 card w-full border rounded-lg">
        <div className="flex justify-between">
          <div>{product.id}</div>
          <div>{product.name}</div>
        </div>
      </div>
    </div>
  )
}
export default ProductListItem
