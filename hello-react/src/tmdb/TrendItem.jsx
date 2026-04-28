const TrendItem = ({ items }) => {
  return (
    <>
      {items.map((item) => (
        <div className="trend-card" key={item.id}>
          <img src={item.poster} alt={item.name} />
          <p>{item.name}</p>
          <span>{item.openDate}</span>
        </div>
      ))}
    </>
  );
};

export default TrendItem;
