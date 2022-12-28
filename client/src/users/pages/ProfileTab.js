import './profile.css';

const ProfileTab = ({rep, about, tags}) => {
    console.log(tags);
    return (
        <div id="profile-wrapper">
            <div id="profile-stats">
                <div className='mini-title'>Stats</div>
                <div id="stats-container">
                    <div className="stats-numbers">{rep}</div>
                    <div className="stats-items">reputation</div>
                    <div className="stats-numbers">0</div>
                    <div className="stats-items">answers</div>
                    <div className="stats-numbers">0</div>
                    <div className="stats-items">questions</div>
                </div>
            </div>
            <div id="about-and-tags">
                <div id="about">
                    <div className='mini-title'>About</div>
                    <div id="about-container">
                        {about}
                    </div>
                </div>
                <div id="tags">
                    <div className='mini-title'>Tags</div>
                    <div id="tags-container">
                        <ul className='tag-list-container'>
                        {tags.map((e) => {
                            return <div className='tag-list-item'>{e.tagName}</div>
                        })}
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ProfileTab;
//reputation만 받아옴. 더미데이터에 질문답변 없어서 추가하기 귀찮..나중에 User.js에서 props로 받아오길
//rep는 object라서 바로 {rep}로 찍으면 에러남. answer랑 questions의 경우에도 콘솔로 찍어서 확인해보자