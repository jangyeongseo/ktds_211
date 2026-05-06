import { ToolkitProvider } from "./stores/toolkit/slices/ToolkitProvider.jsx";
import HelloRouter from "./router/helloRouter.jsx";
import "./index.css";

export default function App() {
  return (
    <ToolkitProvider>
      <HelloRouter />
    </ToolkitProvider>
  );
}
