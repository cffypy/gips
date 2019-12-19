package top.geomatics.ips.service;

/**
 * 定位场景
 */
public interface ILocationScene {
    /**
     *定位结果所处场景：室内场景
     */
    public static final int	LOCATION_SCENE_INDOOR=29;
    /**
     * 定位结果所处场景：室外场景
     */
    public static final int	LOCATION_SCENE_OUTDOOR=30;
    /**
     * 定位结果所处场景：未知场景
     */
    public static final int	LOCATION_SCENE_UNKNOWN=31;
}
