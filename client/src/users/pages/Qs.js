const Qs = ({qs}) => {
    const {questionId, title, vote, created} = qs;
    return (
    <div id="qs-wrapper">
        <div id="vote-box">{vote}</div>
        <div id="qs-title">{title}</div>
        <div id="qs-date">{created}</div>
    </div>
    )
}
export default Qs;