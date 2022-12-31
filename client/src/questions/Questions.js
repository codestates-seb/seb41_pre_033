import "./questions.css";
import { useState, useEffect } from "react";
import QuestionItem from "./questionItem";
import { Link } from "react-router-dom";
import Ask from "./pages/ask/ask";
import axios from "axios";

function Questions() {
  const getQuestionDomain = "/questions?";

  const [isLoding, setIsLoading] = useState(true);
  const [questionList, setQuestionList] = useState([]);
  const [tabNum, setTabNum] = useState(0);
  const [pageNum, setPageNum] = useState(1);
  // tabNum -> undifined 혹은 0 일때
  const tabList = ["newest", "bountied", "unanswered"];

  useEffect(() => {
    function getQ(domain, pageNum, tab) {
      axios(`${domain}page=${pageNum}&tab=${tab}`)
        .then((data) => data.data.data)
        .then((data) => {
          setQuestionList(data);
        });
    }
    getQ(getQuestionDomain, pageNum, tabList[tabNum]);
  }, [tabNum, pageNum]);

  return (
    <div id="questionWrapper">
      <div id="question__header">
        <div className="qusestion__header--top">
          <h1 className="question__header--title">All Questions</h1>
          <Link to="/questions/ask" element={<Ask />}>
            <button className="question__header--button">Ask Question</button>
          </Link>
        </div>
        <div className="question__header--filter">
          <button
            className="question__header--filterButton"
            onClick={() => {
              setTabNum(0);
            }}
          >
            Newest
          </button>
          <button
            className="question__header--filterButton"
            onClick={() => {
              setTabNum(1);
            }}
          >
            Bountied
          </button>
          <button
            className="question__header--filterButton"
            onClick={() => {
              setTabNum(2);
            }}
          >
            Unanswered
          </button>
        </div>
        <div id="question__item--wrapper">
          {questionList.map((e) => (
            <QuestionItem key={e.questionid} item={e} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Questions;
