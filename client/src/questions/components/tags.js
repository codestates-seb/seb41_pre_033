const Tags = ({ tags }) => {
  return (
    <div className="tags-wrapper">
      {tags.map((e) => {
        return <button>{e.tagName}</button>;
      })}
      {/* <button>tagdummy1</button>
      <button>tagdummy2</button> */}
    </div>
  );
};

export default Tags;
