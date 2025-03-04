import Skeleton from "react-loading-skeleton";
import "react-loading-skeleton/dist/skeleton.css";

type ChatSkeletonProps = {
  numberOfCards: number;
};

export default function ChatSkeleton({ numberOfCards }: ChatSkeletonProps) {
  return Array(numberOfCards)
    .fill(0)
    .map((_item, index) => (
      <div className="flex items-center space-x-3 p-2 ml-2" key={index}>
        <Skeleton circle width={50} height={50} />
        <div className="flex-1">
          <Skeleton width={100} height={16} />
          <Skeleton width={120} height={14} style={{ marginTop: 4 }} />
        </div>
        <Skeleton width={50} height={14} />
      </div>
    ));
}
