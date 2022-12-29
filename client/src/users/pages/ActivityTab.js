import './profile.css';

const ActivityTab = () => {
    return (
        <div id="activity-wrapper">
            <div className="quarter">
                <div className="mini-title">Answers</div>
                <div className="answers profile-container">No Answer Yet!</div>
            </div>
            <div className="quarter">
                <div className="mini-title">Questions</div>
                <div className="questions profile-container">No Question Yet!</div>
            </div>
            <div className="quarter">
                <div className="mini-title">Active Bounties</div>
                <div className="bounties profile-container">No Bounty Yet!</div>
            </div>
            <div className="quarter">
                <div className="mini-title">Votes cast</div>
                <div className="votes profile-container">No Vote Casted Yet!</div>
            </div>
        </div>
    )
}

export default ActivityTab;