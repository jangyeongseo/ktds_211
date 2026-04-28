import TrendSelect from "./TrendSelect";
import trend from "./trend.json";

const TrendHeader = ({ type, setType }) => {
  return (
    <div className="trendhead">
      <h3>{trend.sectionName}</h3>

      <div className="header-list">
        <TrendSelect type={type} setType={setType} />
      </div>
    </div>
  );
};

export default TrendHeader;
