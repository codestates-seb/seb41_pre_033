function Pagination({ total, page, setPage }) {
    const numPages = Math.ceil(total / 36);
  
    return (
        <div>
          <button onClick={() => setPage(page - 1)} disabled={page === 1}>
            &lt;
          </button>
          <div>
          {Array(numPages)
            .fill()
            .map((_, i) => (
              <button
                key={i + 1}
                onClick={() => setPage(i + 1)}
                aria-current={page === i + 1 ? "page" : null}
              >
                {i + 1}
              </button>
            ))}
            </div>
          <button onClick={() => setPage(page + 1)} disabled={page === numPages}>
            &gt;
          </button>
          </div>
    );
  }

  export default Pagination;