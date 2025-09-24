

import { Routes, Route } from 'react-router-dom'

import Home from './pages/Home/Home'
import ProductRegister from './pages/ProductRegister/ProductRegister'
import ProductList from './pages/ProductList/ProductList'

import './App.css'

function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/product-register" element={<ProductRegister />} />
        <Route path="/product-list" element={<ProductList />} />
      </Routes>
    </>
  )
}

export default App
