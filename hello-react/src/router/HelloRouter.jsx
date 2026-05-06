import { createBrowserRouter, RouterProvider } from "react-router-dom";
import TrendBox from "../tmdb/TrendBox";
import TodoMain from "../component/todo/TodoMain";
import ArticleMain2 from "../component/articlesInstructor/ArticleMain2";
import { MainLayout } from "../component/layout/MainLayout";
import { NotFoundPage } from "../component/layout/error/NotFoundPage";
import { ArticleLayout } from "../component/layout/ArticleLayout";
import { ArticleDetail } from "../component/articlesInstructor/ArticleDetail";

const HelloRouter = () => {
  // Route 설정.
  const router = createBrowserRouter([
    {
      path: "/",
      element: <MainLayout />,
      errorElement: <NotFoundPage />,
      children: [
        {
          path: "tmdb",
          element: <TrendBox />,
        },
        {
          path: "todo",
          element: <TodoMain />,
        },
        {
          path: "article",
          element: <ArticleLayout />,
          children: [
            {
              index: true, // path: "" 와 같은 의미
              element: <ArticleMain2 />,
            },
            {
              path: ":id",
              element: <ArticleDetail />,
            },
          ],
        },
      ],
    },
  ]);

  // Router Component 생성
  return <RouterProvider router={router} />;
};
export default HelloRouter;
