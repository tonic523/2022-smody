import { ChallengerListProps } from './type';
import { useGetChallengersById } from 'apis';

export const useChallengerList = ({ challengeId }: ChallengerListProps) => {
  const { data: challengersData } = useGetChallengersById(
    { challengeId },
    {
      refetchOnWindowFocus: false,
    },
  );

  return challengersData;
};
