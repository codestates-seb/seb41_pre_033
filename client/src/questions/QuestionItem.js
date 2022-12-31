import { Link } from "react-router-dom";
import Tags from "./components/tags";
import UserInfo from "./components/userInfo";
import axios from "axios";
import "./questions.css";

export default function QuestionItem({ item }) {
  const {
    nickname,
    viewed,
    vote,
    title,
    body,
    questionTags,
    created,
    bounty,
    answers,
  } = item;

  return (
    <div className="question-wrapper">
      <div className="question-left">
        <label>Votes: </label>
        <span>{vote}</span>
        <label>Answers: </label>
        <span>{answers}</span>
        <label>Views: </label>
        <span>{viewed}</span>
        <label>Bounty: </label>
        <span>{bounty}</span>
      </div>
      <div className="question-right">
        <h2 className="question-title">{title}</h2>
        <p className="question-body">{body}</p>
        <div className="question-rightbottom">
          <Tags key={created} tags={questionTags} />
          <UserInfo />
        </div>
      </div>
    </div>
  );
}
