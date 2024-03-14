import React, { type ReactElement } from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'

import Layout from './views/Layout'
import NotFoundView from './views/errors/NotFound'
import HomeView from './views/Home'

const App = (): ReactElement => {
  const router = createBrowserRouter([
    {
      element: <Layout />,
      errorElement: <NotFoundView />,
      children: [
        {
          path: '',
          element: <HomeView />
        }
      ]
    }
  ])

  return (
    <RouterProvider router={router} />
  )
}

export default App
