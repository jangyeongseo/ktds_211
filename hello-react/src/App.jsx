/** @format */

import React from "react";
import TodoMain from "./component/todo/TodoMain.jsx";
import ArticleMain from "./component/articles/ArticleMain.jsx";
import { AssignmentMain } from "./component/assignment/AssignmentMain.jsx";
import ArticleMain2 from "./component/articlesInstructor/ArticleMain2.jsx";
import TrendBox from "./tmdb/TrendBox.jsx";
import { ReactReduxProvider } from "./stores/redux/ReactReduzProvider.jsx";
import "./index.css";
import { ToolkitProvider } from "./stores/toolkit/slices/ToolkitProvider.jsx";

export default function App() {
  return (
    <ToolkitProvider>
      {/* <TrendBox /> */}
      <TodoMain />
      {/* <ArticleMain2 /> */}
      {/* <ArticleMain /> */}
      {/* <AssignmentMain /> */}
    </ToolkitProvider>
  );
}
