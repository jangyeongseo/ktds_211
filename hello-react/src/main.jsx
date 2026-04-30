import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
// 태그로 인식한다.

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <App />
  </StrictMode>,
);
