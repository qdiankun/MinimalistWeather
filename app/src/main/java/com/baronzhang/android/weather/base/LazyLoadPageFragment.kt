package com.baronzhang.android.weather.base

import android.os.Bundle

/**
 * 用于实现 Fragment 懒加载，通常在 ViewPager 场景下使用，否者可以直接继承 BaseFragment
 *
 * @author baronzhang (公众号：BaronTalk)
 */
abstract class LazyLoadPageFragment : BaseFragment() {

    var isViewInitiated: Boolean = false
    var isVisibleToUser: Boolean = false
    var isDataInitiated: Boolean = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInitiated = true
        prepareFetchData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        prepareFetchData()
    }

    /**
     * 获取页面数据
     */
    abstract fun fetchData()

    /**
     * 是否需要强制刷新
     */
    fun isNeedForceUpdate(): Boolean {
        return false
    }

    private fun prepareFetchData(): Boolean {
        if (isViewInitiated && isVisibleToUser && (!isDataInitiated || isNeedForceUpdate())) {
            isDataInitiated = true
            fetchData()
            return true
        }
        return false
    }
}