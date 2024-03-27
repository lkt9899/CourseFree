const arrangeUserFavInfo = (categories, onBoardingQuestions) => {
  const selected = []
  categories.forEach((e, i) => {
    if (e) selected.push(onBoardingQuestions[i]['category'])
  })
  const info = {
    first: selected[0],
    second: selected[1],
    third: selected[2],
  }
  return info
}
export default arrangeUserFavInfo
