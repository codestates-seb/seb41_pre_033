import "./question.css";
import { useState, useEffect } from "react";
import QuestionItem from "./QuestionItem";

function Questions() {
  const [questionList, setQuestionList] = useState([
    { id: 1, test: "하이" },
    { id: 2, test: "하이" },
    { id: 3, test: "하이" },
  ]);

  useEffect(() => {});

  return (
    <div className="questionWrapper">
      <div className="question__header">
        <div className="qusestion__header--top">
          <h1 className="question__header--title">All Questions</h1>
          <button className="question__header--button">Ask Question</button>
        </div>
        <div className="question__header--filter">
          <button className="question__header--filterButton">Newest</button>
          <button className="question__header--filterButton">Bountied</button>
          <button className="question__header--filterButton">Unanswered</button>
        </div>
        {questionList.map((e) => (
          <QuestionItem key={e.id} item={e} />
        ))}
      </div>
    </div>
  );
}

export default Questions;
