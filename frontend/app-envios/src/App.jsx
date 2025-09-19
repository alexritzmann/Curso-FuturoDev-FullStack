

import { Routes, Route } from 'react-router-dom'

import Home from './pages/Home/Home'
import ProductRegister from './pages/ProductRegister/ProductRegister'

import './App.css'

function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/product-register" element={<ProductRegister />} />
      </Routes>
    </>
  )
}

export default App
