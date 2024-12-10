import React from "react";
import { Outlet } from "react-router-dom";


const Layout = () => {
  console.log("Layout component rendered");
  return (
    <main>
      <Outlet/>
    </main>
  )
}

export default Layout
