import React, { type ReactElement } from 'react'
import { Link } from 'react-router-dom'
import { PRODUCT_LIST_PATH } from '../../constants/routes';

const Navbar = (): ReactElement => {
  return (
    <div>
      <Link to={PRODUCT_LIST_PATH}>Produkty</Link>
    </div>
  )
}

export default Navbar
