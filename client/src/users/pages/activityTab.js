import './profile.css';
import Qs from './Qs';

const ActivityTab = ({ans, qs}) => {
    return (
        <div id="activity-wrapper">
            <div className="quarter">
                <div className="mini-title">Answers</div>
                <div className="answers profile-container">
                    {ans.length===0 ? 
                    <div>No Answer Yet!</div>
                    : <div>{ans.length}</div>}
                </div>
            </div>
            <div className="quarter">
                <div className="mini-title">Questions</div>
                <div className="questions profile-container">
                    {qs.length===0 ?
                    <div>No Question Yet!</div>
                    : <div>{qs.map((e) => {
                        return <Qs key={e.questionId} qs={e} />
                    })}</div>}
                </div>
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