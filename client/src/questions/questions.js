import './question.css';
import { useState, useEffect } from 'react';
import QuestionItem from './questionItem';
import { Link } from 'react-router-dom';
import Ask from './pages/ask/ask';
// import { axios } from "axios";

function Questions() {
  const axios = require('axios');
  const getQuestionDomain =
    'http://ec2-13-125-198-24.ap-northeast-2.compute.amazonaws.com:8080/questions?';

  const [isLoding, setIsLoading] = useState(true);
  const [questionList, setQuestionList] = useState([]);
  const [tabNum, setTabNum] = useState(0);
  const [pageNum, setPageNum] = useState(1);
  // tabNum -> undifined 혹은 0 일때
  const tabList = ['newest', 'bountied', 'unanswered'];

  async function getQ(domain, pageNum, tab) {
    try {
      const res = await fetch(`${domain}page=${pageNum}&tab=${tab}`).then((e) =>
        e.json(),
      );
      return res;
    } catch (err) {
      console.log(err);
    }
  }
  useEffect(() => {
    function getQ(domain, pageNum, tab) {
      fetch(`${domain}page=${pageNum}&tab=${tab}`)
        .then((e) => e.json())
        .then((data) => data.data)
        .then((data) => {
          setIsLoading(!isLoding);
          console.log(data[0].questionId);
          setQuestionList(data);
        });
    }
    getQ(getQuestionDomain, pageNum, tabList[tabNum]);
  }, [tabNum, pageNum]);

  function getQ_Handler() {
    const ques = getQ(getQuestionDomain, pageNum, tabList[tabNum]);
    ques
      .then((data) => data.data)
      .then((data) => {
        setIsLoading(!isLoding);
        console.log(data[0].questionId);
        setQuestionList(data);
      });
  }
  return (
    <div className="questionWrapper">
      <button onClick={getQ_Handler}>질문</button>
      <div className="question__header">
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
        {questionList.map((e) => (
          <QuestionItem key={e.questionid} item={e} />
        ))}
      </div>
    </div>
  );
}

export default Questions;
