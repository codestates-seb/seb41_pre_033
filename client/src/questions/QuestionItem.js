import { Link } from "react-router-dom";
import Tags from "./components/Tags";
import UserInfo from "./components/UserInfo";

export default function QuestionItem() {
  return (
    <div className="question-wrapper">
      <div className="question-left">
        <span>votes</span>
        <span>answers</span>
        <span>views</span>
        <span>바운티</span>
      </div>
      <div className="question-right">
        <h2 className="question-title">질문 제목</h2>
        <p className="question-body">질문 바디</p>
        <div className="question-rightbottom">
          <Tags />
          <UserInfo />
        </div>
      </div>
    </div>
  );
}
