import { useCallback, useState } from "react";

const AddClaculator = () => {
  // 결과값 상태 (합계)
  const [addResult, setAddResult] = useState(0);

  // 시작 숫자 상태
  const [startNum, setStartNum] = useState(1);

  // 끝 숫자 상태
  const [endNum, setEndNum] = useState(10);

  /**
   * useCallback
   *
   * "함수를 메모리에 저장(캐싱)해서 재사용"하는 Hook
   *
   * 왜 사용하나?
   * - 컴포넌트가 렌더링될 때마다 함수가 새로 만들어지는 것을 방지
   * - (특히) memo된 자식 컴포넌트에 함수 props를 넘길 때 중요
   *
   * 동작 방식
   * - 의존성 배열([startNum, endNum]) 안의 값이 바뀌면 → 함수 새로 생성
   * - 값이 그대로면 → 기존 함수 재사용
   *
   * 여기서 의미
   * - startNum, endNum이 바뀔 때만 add 함수가 다시 만들어짐
   * - 그 외에는 이전 함수 계속 사용
   */
  const add = useCallback(() => {
    console.log(startNum, endNum);

    let sum = 0;

    // 문자열로 들어올 수 있기 때문에 숫자로 변환
    for (let i = parseInt(startNum); i <= parseInt(endNum); i++) {
      sum += i;
    }

    setAddResult(sum);
  }, [startNum, endNum]);
  // 이 값들이 바뀌면 함수도 새로 생성됨
  // 다시 케싱하여 값이 바뀐다고 생각하면됨.

  return (
    <div>
      {/* 시작 숫자 입력 */}
      <input
        type="number"
        value={startNum}
        onChange={(event) => {
          // input은 항상 문자열로 들어오기 때문에 그대로 상태에 저장됨
          setStartNum(event.target.value);
        }}
      />{" "}
      ~ {/* 끝 숫자 입력 */}
      <input
        type="number"
        value={endNum}
        onChange={(event) => {
          setEndNum(event.target.value);
        }}
      />{" "}
      = <span>{addResult}</span>
      <div>
        {/* 버튼 클릭 시 add 함수 실행 */}
        <button type="button" onClick={add}>
          계산하기
        </button>
      </div>
    </div>
  );
};

export default AddClaculator;
