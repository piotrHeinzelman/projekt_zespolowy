import React, { type ReactElement } from 'react'
import { ProductData } from '../../constants/types'
import { Link } from 'react-router-dom'
import { PRODUCT_DETAILS_PATH } from '../../constants/routes'

const ProductListItem = ({ product, admin }: { product: ProductData, admin: boolean }): ReactElement => {
  return (
    <>
      <tr className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
        <td className="p-4">
          <img src="/docs/images/products/apple-watch.png" className="w-16 md:w-32 max-w-full max-h-full"
               alt="ProductImage" />
        </td>
        <Link to={PRODUCT_DETAILS_PATH.replace(':sku', product.sku)}>
          <td className="px-6 py-4 font-semibold text-gray-900 dark:text-white hover:underline">
            {product.name}
          </td>
        </Link>
        <td className="px-6 py-4 font-semibold text-gray-900 dark:text-white">
          {/* FIXME: Fix for the product.price */}
          ${product.sku}
        </td>
        <td className="px-6 py-4">
          {
            admin ? (
              <a href="#" className="font-medium text-blue-600 dark:text-red-500 hover:underline">
                Edit
              </a>
            ) : (
              <a href="#" className="font-medium text-blue-600 dark:text-red-500 hover:underline">
                Add to cart
              </a>
            )
          }
        </td>
      </tr>
    </>
  )
}
export default ProductListItem
