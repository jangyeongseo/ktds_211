// CommonJS
// const { createContext } = require("react");
import { createContext } from "react";

const TodoContext = createContext({
  // 인터페이스
  componentName: "",
});
export default TodoContext;
