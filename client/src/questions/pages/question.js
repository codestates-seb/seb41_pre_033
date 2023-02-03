import UserInfo from "../components/UserInfo";
import Tags from "../components/Tags";

export default function Question() {
  return (
    <div>
      <div>
        <div>
          <h1>질문제목</h1>
          <button>질문하기</button>
        </div>
        <div>
          <span>질문 작성일</span>
          <span>질문 조회수</span>
        </div>
      </div>
      <div>
        <div>
          <button>업</button>
          <span>Vote</span>
          <button>다운</button>
        </div>
        <div>
          <p>질문 내용</p>
          <Tags />
          <div>
            <button>edit</button>
            <button>delete</button>
            <UserInfo />
          </div>
          <div>(임시) 답변</div>
        </div>
      </div>
    </div>
  );
}
