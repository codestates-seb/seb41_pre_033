import Guide from "./components/Guide";
import { useState } from "react";

export default function Ask() {
  const [question, setQuestion] = useState({});

  const Title = () => {
    return (
      <div>
        <h3>Title</h3>
        <br />
        <div>
          Be specific and imagine you’re asking a question to another person.
        </div>
        <input
          type="text"
          placeholder="예시"
          onChange={(e) => {
            setQuestion(Object.assign(question, { title: e.target.value }));
          }}
          value={question.title}
        ></input>
        <button>Next</button>
      </div>
    );
  };

  function Problem() {
    return (
      <div>
        <h3>What are the details of your problem?</h3>
        <br />
        <div>
          Introduce the problem and expand on what you put in the title. Minimum
          20 characters.
        </div>
        <input
          type="text"
          onChange={(e) => {
            setQuestion(Object.assign(question, { problem: e.target.value }));
          }}
        ></input>
      </div>
    );
  }
  function Expect() {
    return (
      <div>
        <h3>What did you try and what were you expecting?</h3>
        <br />
        <div>
          Describe what you tried, what you expected to happen, and what
          actually resulted. Minimum 20 characters.
        </div>
        <input
          type="text"
          onChange={(e) => {
            setQuestion(Object.assign(question, { expect: e.target.value }));
          }}
        ></input>
      </div>
    );
  }

  const Tags = () => {
    return (
      <div>
        <h3>Tags</h3>
        <br />
        <div>
          Add up to 5 tags to describe what your question is about. Start typing
          to see suggestions.
        </div>
        <input
          type="text"
          placeholder="예시"
          onChange={(e) => {
            setQuestion(Object.assign(question, { tags: e.target.value }));
          }}
          value={question.tags}
        ></input>
      </div>
    );
  };

  return (
    <div>
      <h1>Ask a public question</h1>
      <Guide />
      <Title />
      <Problem />
      <Expect />
      <Tags />
      <button
        onClick={() => {
          console.log(question);
        }}
      >
        콘솔에서 확인
      </button>
    </div>
  );
}
