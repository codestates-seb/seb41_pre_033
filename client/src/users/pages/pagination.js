import './pagination.css';

const Pagination = ({ page, setPage }) => {
    const numPages = 2;
    //일단 두개만 해둠. 나중에 필요하면 토탈 유저수 받아와서 페이지당개수(36)로 나눠주자
  
    return (
        <div id="page-button-container">
          <button className="page-button" onClick={() => setPage(page - 1)} disabled={page === 1}>
            &lt;
          </button>
          <div>
          {Array(numPages)
            .fill()
            .map((_, i) => (
              <button className="page-button"
                key={i + 1}
                onClick={() => setPage(i + 1)}
                aria-current={page === i + 1 ? "page" : null}
              >
                {i + 1}
              </button>
            ))}
            </div>
          <button className="page-button" onClick={() => setPage(page + 1)} disabled={page === numPages}>
            &gt;
          </button>
          </div>
    );
  }

  export default Pagination;