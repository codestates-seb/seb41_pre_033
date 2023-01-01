import Guide from "./components/Guide";
import { useState, useRef } from "react";
import axios from "axios";
import { redirect, useNavigate } from "react-router-dom";

export default function Ask() {
  const [question, setQuestion] = useState({ userId: "3" });
  const [tags, setTags] = useState("");
  const [testN, setTestN] = useState(false);
  const problemInput = useRef(null);
  let tagsRaw = "";
  const navigate = useNavigate();

  function askHandler() {
    axios.post("/questions/ask", question).then((res) => {
      if (res.status === 201) {
        console.log(res.status);
        navigate("/a");
      }
    });
  }

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
        <button
          onClick={() => {
            problemInput.current.focus();
          }}
        >
          Next
        </button>
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
            setQuestion(Object.assign(question, { body: e.target.value }));
          }}
          value={question.body}
          ref={problemInput}
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
          Add up to 5 tags to describe what your question is about. 태그는
          띄어쓰기로 구분합니다.
        </div>
        <input
          type="text"
          placeholder="예시"
          onChange={(e) => {
            tagsRaw = e.target.value;
            let tagsEach = tagsRaw.split(" ");
            let tagsArr = tagsEach.map((e) => {
              return { tagName: e };
            });
            setQuestion(Object.assign(question, { questionTags: tagsArr }));
          }}
          value={question.tags}
        ></input>
      </div>
    );
  };

  return (
    <div>
      <h1 className="">Ask a public question</h1>
      <Guide />
      <Title />
      <Problem />
      <Tags />
      <button
        onClick={() => {
          console.log(question);
        }}
      >
        콘솔에서 확인
      </button>
      <button onClick={askHandler}>Submit</button>
      <button
        onClick={() => {
          return redirect("/a");
        }}
      >
        리다이렉트?
      </button>
      {/* {testN ? <Redirect to="/a" /> : ""} */}
    </div>
  );
}
