import TrendItem from "./TrendItem";

const TrendList = ({ items }) => {
  return (
    <div className="trend-list">
      <TrendItem items={items} />
    </div>
  );
};

export default TrendList;
