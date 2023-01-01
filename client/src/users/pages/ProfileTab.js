import './profile.css';

const ProfileTab = ({rep, about, tags, ans, qs}) => {
    return (
        <div id="profile-wrapper">
            <div id="profile-stats">
                <div className='mini-title'>Stats</div>
                <div id="stats-container">
                    <div className="stats-numbers">{rep}</div>
                    <div className="stats-items">reputation</div>
                    <div className="stats-numbers">{ans}</div>
                    <div className="stats-items">answers</div>
                    <div className="stats-numbers">{qs}</div>
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