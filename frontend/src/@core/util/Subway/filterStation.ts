export function filter(stationList) {
  const placeToIndexMap = {}
  const placevisitSet = new Set()
  const returnArr = []
  for (let i = 0; i < stationList.length; i++) {
    const station = stationList[i]
    const name = station.stationName
    if (!placevisitSet.has(name)) {
      placevisitSet.add(name)
      placeToIndexMap[name] = i
      returnArr.push({
        line: [station.line],
        stationName: name,
        point: station.point,
      })
    } else {
      const idx = placeToIndexMap[name]
      returnArr[idx]['line'].push(station.line)
    }
  }
  return returnArr
}
