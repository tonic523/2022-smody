import { ChallengerProps } from './type';
import styled from 'styled-components';

import useThemeContext from 'hooks/useThemeContext';

import { FlexBox } from 'components/@shared/FlexBox';
import { Text } from 'components/@shared/Text';

import { CheckCircles } from 'components/CheckCircles';

export const Challenger = ({
  memberId,
  nickName,
  progressCount,
  picture,
  introduction,
}: ChallengerProps) => {
  const themeContext = useThemeContext();

  return (
    <Wrapper justifyContent="space-between" alignItems="center">
      <FlexBox gap="17px" alignItems="center">
        <ProfileImg src={picture} alt={`${nickName} 프로필 사진`} />
        <FlexBox flexDirection="column" gap="0.5rem">
          <Text size={16} color={themeContext.onSurface}>
            {nickName}
          </Text>
          <Text size={12} color={themeContext.mainText}>
            {introduction}
          </Text>
        </FlexBox>
      </FlexBox>
      <div>
        <CheckCircles progressCount={progressCount} />
      </div>
    </Wrapper>
  );
};

const Wrapper = styled(FlexBox)`
  ${FlexBox} {
    flex-grow: 1;
  }
  div {
    flex-grow: 0;
  }
`;

const ProfileImg = styled.img`
  width: 2.56rem;
  height: 2.56rem;
  border-radius: 50%;
`;