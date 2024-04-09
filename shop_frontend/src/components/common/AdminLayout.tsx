import React, { type ReactElement } from "react"
import { Outlet } from "react-router-dom";

export const AdminLayout = (): ReactElement => {
  return <Outlet />
}

export default AdminLayout
