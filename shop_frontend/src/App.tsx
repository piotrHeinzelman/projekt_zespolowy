import React, { type ReactElement } from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'

import Layout from './components/common/Layout'
import NotFoundView from './views/errors/NotFound'
import HomeView from './views/Home'
import {
  PRODUCT_LIST_PATH,
  PRODUCT_DETAILS_PATH,
  ADMIN_PANEL_PATH, ADMIN_NEW_PRODUCT_PATH
} from './constants/routes';
import ProductListView from "./views/ProductListView";
import ProductDetailsView from "./views/ProductDetailsView";

// ADMIN
import AdminDashboardView from "./views/admin/AdminDashboardView";
import AdminLayout from "./components/common/AdminLayout";
import AdminNewProductView from "./views/admin/AdminNewProductView";

const App = (): ReactElement => {
  const router = createBrowserRouter([
    {
      element: <Layout />,
      errorElement: <NotFoundView />,
      children: [
        {
          path: '',
          element: <HomeView />
        },
        {
          path: PRODUCT_LIST_PATH,
          element: <ProductListView />
        },
        {
          path: PRODUCT_DETAILS_PATH,
          element: <ProductDetailsView />
        },
        {
          path: ADMIN_PANEL_PATH,
          element: <AdminLayout />,
          children: [
            {
              path: '',
              element: <AdminDashboardView />
            },
            {
              path: ADMIN_NEW_PRODUCT_PATH,
              element: <AdminNewProductView />
            }
          ]
        }
      ]
    }
  ])

  return (
    <RouterProvider router={router} />
  )
}

export default App
