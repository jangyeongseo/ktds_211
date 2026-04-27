/** @format */

import { useImperativeHandle, useRef, useState } from "react";
import { createPortal } from "react-dom";

export const Alert = ({ dialogRef }) => {
  console.log("Alert");

  // 실제 <dialog> DOM을 잡는 ref
  const alertModalRef = useRef();

  // 화면에 보여줄 메시지 (state → 바뀌면 화면 다시 렌더됨)
  const [errorMessage, setErrorMessage] = useState();

  /**
   * 핵심: 부모가 사용할 수 있는 "함수 API"를 만들어주는 부분
   *
   * 부모는 이렇게 사용함:
   * alertRef.current.showModal("에러 메시지")
   */
  useImperativeHandle(dialogRef, () => {
    return {
      // 부모가 호출할 함수
      showModal(message) {
        // 1. dialog 열기
        alertModalRef.current.showModal();

        // 2. 메시지 설정 (state라서 화면 다시 렌더됨)
        setErrorMessage(message);
      },
    };
  });

  /**
   * X 버튼 클릭 시 실행
   */
  const onCloseClickHandler = () => {
    alertModalRef.current.close(); // dialog 닫기
  };

  return (
    <>
      {createPortal(
        <dialog className="modal" ref={alertModalRef}>
          <div className="modal-body">
            {/* 닫기 버튼 */}
            <section
              className="modal-close-button"
              onClick={onCloseClickHandler}
            >
              X
            </section>

            {/* 메시지 출력 */}
            <div>{errorMessage}</div>
          </div>
        </dialog>,
        document.querySelector("#modals"),
      )}
    </>
  );
};

export const Confirm = ({ dialogRef, onOkClick, onCloseClick }) => {
  console.log("Confirm");

  // confirm 메시지
  const [confirmMessage, setConfirmMessage] = useState();

  // 실제 dialog DOM 잡는 ref
  const confirmDialogRef = useRef();

  /**
   *  이벤트 추적용 ref (중요)
   *
   * "OK / Cancel 버튼으로 닫았는지"
   * "ESC로 닫았는지"
   * 구분하려고 사용
   *
   * ref라서 값 바뀌어도 화면 재렌더 안됨
   */
  const handledFromEvents = useRef({
    fired: false,
  });

  /**
   *  부모가 호출할 수 있는 API
   *
   * 부모:
   * confirmRef.current.showConfirm("진짜 삭제?")
   */
  useImperativeHandle(dialogRef, () => {
    return {
      showConfirm(message) {
        setConfirmMessage(message); // 메시지 설정
        confirmDialogRef.current.showModal(); // dialog 열기
      },
    };
  });

  /**
   * OK 버튼 클릭
   */
  const onOkClickHandler = () => {
    handledFromEvents.current.fired = true; // "버튼으로 닫았다"
    confirmDialogRef.current.close(); // dialog 닫기

    onOkClick(); // 부모가 넘긴 OK 콜백 실행
  };

  /**
   * Cancel 버튼 클릭
   */
  const onCloseClickHandler = () => {
    handledFromEvents.current.fired = true;
    confirmDialogRef.current.close();

    onCloseClick(); // 부모 콜백 실행
  };

  /**
   *  중요: ESC로 닫힌 경우
   * (버튼 클릭이 아니라 시스템으로 닫힘)
   */
  const onCloseNative = () => {
    if (!handledFromEvents.current.fired) {
      onCloseClick(); // ESC로 닫혔으면 Cancel 처리
    }

    // dialog 가 꺼질 때, false 값을 fals로 변경되도록 한다.
    // event에 의해 닫힌 케이스도 초기화.
    handledFromEvents.current.fired = false;
  };

  return (
    <>
      {createPortal(
        <dialog
          className="modal"
          ref={confirmDialogRef}
          onClose={onCloseNative} // ESC 닫힘 감지
        >
          <div className="modal-body">
            {/* 메시지 */}
            <div>{confirmMessage}</div>

            <section>
              <button
                type="button"
                className="confirm-ok"
                onClick={onOkClickHandler}
              >
                OK
              </button>

              <button
                type="button"
                className="confirm-cancel"
                onClick={onCloseClickHandler}
              >
                Cancel
              </button>
            </section>
          </div>
        </dialog>,
        document.querySelector("#modals"),
      )}
    </>
  );
};
