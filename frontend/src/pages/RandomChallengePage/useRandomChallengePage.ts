import { useGetAllChallenges } from 'apis';
import { indexedDB, saveDataToCache } from 'pwa/indexedDB';
import { useEffect, useState } from 'react';
import { AdditionalChallengeInfo } from 'types/challenge';

import { INDEXED_DB } from 'constants/storage';

export const useRandomChallengePage = () => {
  const { data: challengeInfiniteData, isError } = useGetAllChallenges(
    { sort: 'random' },
    {
      useErrorBoundary: false,
      onSuccess: (data) => {
        const challenges = data.pages[0].data;
        saveDataToCache(INDEXED_DB.RANDOM_CHALLENGE, data.pages.length, challenges);
      },
    },
  );

  const [savedChallenges, setSavedChallenges] = useState<AdditionalChallengeInfo[]>([]);

  useEffect(() => {
    if (!isError) {
      return;
    }
    indexedDB.getPosts(INDEXED_DB.RANDOM_CHALLENGE).then((challenges) => {
      setSavedChallenges(challenges);
    });
  }, [isError]);

  return { challengeInfiniteData, isError, savedChallenges };
};
