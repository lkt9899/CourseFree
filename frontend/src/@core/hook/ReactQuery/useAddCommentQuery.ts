import QUERY_KEY from '@constant/queryKey'
import { queryClient } from '@data/queryClient'
import { useMutation } from '@tanstack/react-query'

import { requestSubmitComment } from '@api/request/article'

export const useAddCommentQuery = () => {
  // 업데이트할 쿼리

  const mutation = useMutation({
    mutationFn: ({ postId, content }: any) => {
      return requestSubmitComment(postId, content)
    },
    // // mutate가 호출되면 onMutate를 실행시킴:
    // onMutate: async newComment => {
    //   // 진행중인 refetch가 있다면 취소시킨다.
    //   // 만약 그러지 않는다면 refetchOnMount등을 true로 해뒀을 때
    //   // 페이지를 들어오자 마자 refetch를 하면 refetch가 두번 실행되고,
    //   // 화면에 최신 데이터를 그려주지 않을 가능성이 있다.
    //   // 그것을 방지하기 위해 cancelQueries를 실행시켜준다.

    //   await queryClient.cancelQueries({
    //     queryKey: [QUERY_KEY.ARTICLE_COMMENT, postId],
    //   })

    //   // 이전 쿼리값의 스냅샷
    //   const previousComments = queryClient.getQueryData([
    //     QUERY_KEY.ARTICLE_COMMENT,
    //     postId,
    //   ])
    //   console.log(previousComments)
    //   // setQueryData 함수를 사용해 newTodo로 Optimistic Update를 실시한다.
    //   queryClient.setQueryData(
    //     [QUERY_KEY.ARTICLE_COMMENT, postId],
    //     (prev: any) => {
    //       console.log(prev, newComment)
    //       return [
    //         ...prev.data,
    //         {
    //           memberImageUrl: '',
    //           memberNickname: '하하',
    //           content: newComment.content,
    //         },
    //       ]
    //     },
    //   )

    //   // context를 리턴하는데 여기에는 이전 스냅샷, 새로운 값을 넣어 리턴해준다.
    //   // 혹은 롤백하는 함수를 여기서 리턴해줘도 된다.
    //   return {
    //     previousComments,
    //     next: {
    //       memberImageUrl: '',
    //       memberNickname: '하하',
    //       content: newComment.content,
    //     },
    //   }
    // },
    // 성공하거나 실패시 쿼리를 무효화해 최신 데이터를 받아와준다.
    onSettled: () => {
      return queryClient.invalidateQueries({
        queryKey: [QUERY_KEY.ARTICLE_COMMENT],
      })
    },
  })

  return mutation
}
