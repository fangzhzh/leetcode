
class Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        def reverse(nums: List[int], left: int, right: int) -> None:
            while left < right:
                nums[left], nums[right] = nums[right], nums[left]
                left += 1
                right -=1
        def reverseReverse()->None:
            reverse(nums, 0, len(nums)-1)
            reverse(nums, 0, k-1)
            reverse(nums, k, len(nums)-1)
        def extraSpace() -> None:
            n = len(nums)
            temp = nums[:]
            k = k % n
            for i in range(n):
                nums[(i+k)%n] = temp[i]
        def slicing() -> None:
            # TC O(N), SC O(N),
            k = k % len(nums)
            nums[:] = nums[-k:] + nums[:-k]