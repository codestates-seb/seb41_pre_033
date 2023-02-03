import { Link } from 'react-router-dom';
import Tags from './components/tags';
import UserInfo from './components/userInfo';

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
        <span>votes</span>
        <span>answers</span>
        <span>views</span>
        <span>바운티</span>
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
