import React, { type ReactElement } from "react"
import { map } from "underscore";
import ProductListItem from "./ProductListItem";
import { ProductData } from "../../constants/types";
import { Button } from "flowbite-react";
import { ADMIN_NEW_PRODUCT_PATH } from "../../constants/routes"

const ProductList = ({ products, admin = false }: { products: ProductData[], admin?: boolean }): ReactElement => {
  return (
    <div className="p-10">
      {
        admin && (
          <div className="flex justify-end p-3">
            <Button href={ADMIN_NEW_PRODUCT_PATH}>Add product</Button>
          </div>
        )
      }
      <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
          <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
          <tr>
            <th scope="col" className="px-16 py-3">
              <span className="sr-only">Image</span>
            </th>
            <th scope="col" className="px-6 py-3">
              Product
            </th>
            <th scope="col" className="px-6 py-3">
              Price
            </th>
            <th scope="col" className="px-6 py-3">
              Action
            </th>
          </tr>
          </thead>
          <tbody>
          {map(products, (product) => (
            // key={product.id} is important for React to keep track of the list items
            <ProductListItem product={product} admin={admin} />
          ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default ProductList

