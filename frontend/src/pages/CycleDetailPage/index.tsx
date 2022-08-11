import useCycleDetailPage from './useCycleDetailPage';
import styled from 'styled-components';
import { parseTime } from 'utils';

import useThemeContext from 'hooks/useThemeContext';

import { Text, ThumbnailWrapper, CycleDetailList, FlexBox, Title } from 'components';

import { CLIENT_PATH } from 'constants/path';
import { emojiList, colorList } from 'constants/style';

const CycleDetailPage = () => {
  const themeContext = useThemeContext();
  const cycleDetailData = useCycleDetailPage();

  if (typeof cycleDetailData === 'undefined') {
    return null;
  }

  const { challengeName, startTime, cycleDetails, emojiIndex, colorIndex } =
    cycleDetailData.data;
  const { year, month, date } = parseTime(startTime);

  return (
    <div>
      <Title text="인증기록 보기" linkTo={CLIENT_PATH.CERT} />
      <ChallengeDetailWrapper
        flexDirection="row"
        justifyContent="space-evenly"
        alignItems="center"
        gap="1rem"
      >
        <FlexBox flexDirection="column" alignItems="center" gap="1rem">
          <Text size={20} fontWeight="bold" color={themeContext.primary}>
            {challengeName}
          </Text>
          <Text color={themeContext.onBackground}>
            {year}년 {month}월 {date}일부터 작심삼일 극복 도전
          </Text>
        </FlexBox>
        <ThumbnailWrapper size="medium" bgColor={colorList[colorIndex]}>
          {emojiList[emojiIndex]}
        </ThumbnailWrapper>
      </ChallengeDetailWrapper>
      <CycleDetailList cycleDetails={cycleDetails} />
    </div>
  );
};

export default CycleDetailPage;

const ChallengeDetailWrapper = styled(FlexBox)`
  line-height: 1rem;
  margin-bottom: 2rem;
`;
