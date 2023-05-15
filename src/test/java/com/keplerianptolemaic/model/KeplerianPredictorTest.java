package com.keplerianptolemaic.model;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.math3.util.MathUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.keplerianptolemaic.utils.EccentricityInvalidException;
import com.keplerianptolemaic.utils.SemiMajorAxisInvalidException;

@RunWith(Parameterized.class)
public class KeplerianPredictorTest {

    private static final double ERROR_TOLERANCE_DEGREES = 2.5;
    private long timeId;
    private double expectedMarsTheta;
 
    private KeplerianPredictor keplerianPredictor;

    public KeplerianPredictorTest(long timeId, double expectedMarsTheta) {
        super();
        this.timeId = timeId;
        this.expectedMarsTheta = expectedMarsTheta;
    }

    @Before
    public void initialize() throws SemiMajorAxisInvalidException, EccentricityInvalidException {
        keplerianPredictor = new KeplerianPredictor();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> timeIdsAndExpectedMarsThetas() {
        return Arrays.asList(new Object[][] {
          { 0, 109.072134 },
          { 1, 108.689417 },
          { 2, 108.306799 },
          { 3, 107.924345 },
          { 4, 107.538358 },
          { 5, 107.156635 },
          { 6, 106.775315 },
          { 7, 106.398353 },
          { 8, 106.02212 },
          { 9, 105.650481 },
          { 10, 105.283525 },
          { 11, 104.921362 },
          { 12, 104.564015 },
          { 13, 104.21552 },
          { 14, 103.872039 },
          { 15, 103.537542 },
          { 16, 103.212012 },
          { 17, 102.895645 },
          { 18, 102.584539 },
          { 19, 102.28658 },
          { 20, 101.997915 },
          { 21, 101.722385 },
          { 22, 101.456349 },
          { 23, 101.199761 },
          { 24, 100.960226 },
          { 25, 100.726485 },
          { 26, 100.509966 },
          { 27, 100.306867 },
          { 28, 100.113288 },
          { 29, 99.933316 },
          { 30, 99.770504 },
          { 31, 99.617304 },
          { 32, 99.477582 },
          { 33, 99.351316 },
          { 34, 99.238616 },
          { 35, 99.139263 },
          { 36, 99.053258 },
          { 37, 98.980817 },
          { 38, 98.92172 },
          { 39, 98.875967 },
          { 40, 98.843557 },
          { 41, 98.824599 },
          { 42, 98.818855 },
          { 43, 98.82262 },
          { 44, 98.83976 },
          { 45, 98.873935 },
          { 46, 98.917472 },
          { 47, 98.970444 },
          { 48, 99.040354 },
          { 49, 99.119747 },
          { 50, 99.212142 },
          { 51, 99.313923 },
          { 52, 99.428719 },
          { 53, 99.556596 },
          { 54, 99.693638 },
          { 55, 99.843666 },
          { 56, 100.002853 },
          { 57, 100.174912 },
          { 58, 100.356103 },
          { 59, 100.546244 },
          { 60, 100.749246 },
          { 61, 100.961153 },
          { 62, 101.181982 },
          { 63, 101.411723 },
          { 64, 101.650286 },
          { 65, 101.901559 },
          { 66, 102.157695 },
          { 67, 102.426445 },
          { 68, 102.700025 },
          { 69, 102.982293 },
          { 70, 103.273244 },
          { 71, 103.57653 },
          { 72, 103.880824 },
          { 73, 104.197624 },
          { 74, 104.522808 },
          { 75, 104.852697 },
          { 76, 105.191016 },
          { 77, 105.537825 },
          { 78, 105.889102 },
          { 79, 106.248894 },
          { 80, 106.61694 },
          { 81, 106.989538 },
          { 82, 107.370413 },
          { 83, 107.75952 },
          { 84, 108.15303 },
          { 85, 108.550866 },
          { 86, 108.956922 },
          { 87, 109.367161 },
          { 88, 109.785625 },
          { 89, 110.212035 },
          { 90, 110.638785 },
          { 91, 111.073644 },
          { 92, 111.516313 },
          { 93, 111.959294 },
          { 94, 112.41025 },
          { 95, 112.868986 },
          { 96, 113.327904 },
          { 97, 113.794642 },
          { 98, 114.265332 },
          { 99, 114.739905 },
          { 100, 115.222226 },
          { 101, 115.704473 },
          { 102, 116.194484 },
          { 103, 116.688193 },
          { 104, 117.185732 },
          { 105, 117.690762 },
          { 106, 118.195767 },
          { 107, 118.708278 },
          { 108, 119.224336 },
          { 109, 119.744052 },
          { 110, 120.267408 },
          { 111, 120.794318 },
          { 112, 121.328541 },
          { 113, 121.86247 },
          { 114, 122.403613 },
          { 115, 122.944439 },
          { 116, 123.492477 },
          { 117, 124.043967 },
          { 118, 124.598709 },
          { 119, 125.152925 },
          { 120, 125.714402 },
          { 121, 126.279009 },
          { 122, 126.846942 },
          { 123, 127.418007 },
          { 124, 127.992286 },
          { 125, 128.56968 },
          { 126, 129.15027 },
          { 127, 129.733869 },
          { 128, 130.32056 },
          { 129, 130.910334 },
          { 130, 131.503182 },
          { 131, 132.098925 },
          { 132, 132.697729 },
          { 133, 133.2995 },
          { 134, 133.90415 },
          { 135, 134.511758 },
          { 136, 135.122214 },
          { 137, 135.731755 },
          { 138, 136.34797 },
          { 139, 136.966941 },
          { 140, 137.58877 },
          { 141, 138.213426 },
          { 142, 138.836996 },
          { 143, 139.467149 },
          { 144, 140.100028 },
          { 145, 140.731899 },
          { 146, 141.370178 },
          { 147, 142.007431 },
          { 148, 142.651098 },
          { 149, 143.293651 },
          { 150, 143.93885 },
          { 151, 144.586703 },
          { 152, 145.240948 },
          { 153, 145.890256 },
          { 154, 146.54604 },
          { 155, 147.204331 },
          { 156, 147.865271 },
          { 157, 148.524966 },
          { 158, 149.187319 },
          { 159, 149.856069 },
          { 160, 150.523597 },
          { 161, 151.193661 },
          { 162, 151.86262 },
          { 163, 152.538006 },
          { 164, 153.215883 },
          { 165, 153.89263 },
          { 166, 154.572013 },
          { 167, 155.25403 },
          { 168, 155.938704 },
          { 169, 156.625985 },
          { 170, 157.312152 },
          { 171, 158.004822 },
          { 172, 158.696321 },
          { 173, 159.386798 },
          { 174, 160.083758 },
          { 175, 160.779711 },
          { 176, 161.482151 },
          { 177, 162.179857 },
          { 178, 162.884182 },
          { 179, 163.587487 },
          { 180, 164.293732 },
          { 181, 165.002808 },
          { 182, 165.711056 },
          { 183, 166.422229 },
          { 184, 167.132613 },
          { 185, 167.846044 },
          { 186, 168.562496 },
          { 187, 169.278254 },
          { 188, 169.997156 },
          { 189, 170.715419 },
          { 190, 171.43691 },
          { 191, 172.161633 },
          { 192, 172.885837 },
          { 193, 173.609618 },
          { 194, 174.336785 },
          { 195, 175.067351 },
          { 196, 175.797586 },
          { 197, 176.527575 },
          { 198, 177.261122 },
          { 199, 177.9945 },
          { 200, 178.731538 },
          { 201, 179.468482 },
          { 202, -179.794575 },
          { 203, -179.053823 },
          { 204, -178.31301 },
          { 205, -177.57204 },
          { 206, -176.827109 },
          { 207, -176.081956 },
          { 208, -175.33651 },
          { 209, -174.590711 },
          { 210, -173.840745 },
          { 211, -173.094122 },
          { 212, -172.343183 },
          { 213, -171.591709 },
          { 214, -170.839645 },
          { 215, -170.086903 },
          { 216, -169.333451 },
          { 217, -168.579261 },
          { 218, -167.824238 },
          { 219, -167.068332 },
          { 220, -166.307749 },
          { 221, -165.550003 },
          { 222, -164.791237 },
          { 223, -164.027616 },
          { 224, -163.266685 },
          { 225, -162.504612 },
          { 226, -161.741338 },
          { 227, -160.976812 },
          { 228, -160.211049 },
          { 229, -159.447711 },
          { 230, -158.679247 },
          { 231, -157.91316 },
          { 232, -157.145628 },
          { 233, -156.376634 },
          { 234, -155.606134 },
          { 235, -154.83411 },
          { 236, -154.064293 },
          { 237, -153.29284 },
          { 238, -152.519811 },
          { 239, -151.748864 },
          { 240, -150.972463 },
          { 241, -150.198195 },
          { 242, -149.425972 },
          { 243, -148.651982 },
          { 244, -147.87621 },
          { 245, -147.098646 },
          { 246, -146.323101 },
          { 247, -145.545761 },
          { 248, -144.77033 },
          { 249, -143.993043 },
          { 250, -143.213965 },
          { 251, -142.433022 },
          { 252, -141.65399 },
          { 253, -140.876892 },
          { 254, -140.097843 },
          { 255, -139.316995 },
          { 256, -138.534273 },
          { 257, -137.753462 },
          { 258, -136.974597 },
          { 259, -136.193789 },
          { 260, -135.411147 },
          { 261, -134.630522 },
          { 262, -133.848008 },
          { 263, -133.063637 },
          { 264, -132.281287 },
          { 265, -131.501007 },
          { 266, -130.718821 },
          { 267, -129.934939 },
          { 268, -129.153055 },
          { 269, -128.369435 },
          { 270, -127.584118 },
          { 271, -126.801002 },
          { 272, -126.019964 },
          { 273, -125.237205 },
          { 274, -124.452858 },
          { 275, -123.670712 },
          { 276, -122.886972 },
          { 277, -122.105547 },
          { 278, -121.322523 },
          { 279, -120.538042 },
          { 280, -119.756021 },
          { 281, -118.976218 },
          { 282, -118.191261 },
          { 283, -117.408718 },
          { 284, -116.628623 },
          { 285, -115.843429 },
          { 286, -115.064664 },
          { 287, -114.280784 },
          { 288, -113.499468 },
          { 289, -112.717102 },
          { 290, -111.937312 },
          { 291, -111.156486 },
          { 292, -110.374683 },
          { 293, -109.595585 },
          { 294, -108.811726 },
          { 295, -108.034656 },
          { 296, -107.252817 },
          { 297, -106.473924 },
          { 298, -105.698022 },
          { 299, -104.917643 },
          { 300, -104.140245 },
          { 301, -103.362202 },
          { 302, -102.583467 },
          { 303, -101.807967 },
          { 304, -101.031885 },
          { 305, -100.255389 },
          { 306, -99.482061 },
          { 307, -98.704519 },
          { 308, -97.930444 },
          { 309, -97.155992 },
          { 310, -96.381119 },
          { 311, -95.609867 },
          { 312, -94.838396 },
          { 313, -94.06285 },
          { 314, -93.291023 },
          { 315, -92.522862 },
          { 316, -91.750761 },
          { 317, -90.97862 },
          { 318, -90.210355 },
          { 319, -89.442127 },
          { 320, -88.670296 },
          { 321, -87.902329 },
          { 322, -87.138284 },
          { 323, -86.370662 },
          { 324, -85.603364 },
          { 325, -84.840063 },
          { 326, -84.073373 },
          { 327, -83.310742 },
          { 328, -83.310742 },
          { 329, -82.544866 },
          { 330, -81.783107 },
          { 331, -81.02195 },
          { 332, -80.261195 },
          { 333, -79.497288 },
          { 334, -78.73768 },
          { 335, -77.978746 },
          { 336, -77.220521 },
          { 337, -76.462915 },
          { 338, -75.706053 },
          { 339, -74.950059 },
          { 340, -74.194714 },
          { 341, -73.436459 },
          { 342, -72.682858 },
          { 343, -71.930146 },
          { 344, -71.174467 },
          { 345, -70.423697 },
          { 346, -69.673758 },
          { 347, -68.921083 },
          { 348, -68.169446 },
          { 349, -67.422733 },
          { 350, -66.673302 },
          { 351, -65.92493 },
          { 352, -65.17781 },
          { 353, -64.431851 },
          { 354, -63.687069 },
          { 355, -62.943549 },
          { 356, -62.197417 },
          { 357, -61.456413 },
          { 358, -60.712873 },
          { 359, -59.97067 },
          { 360, -59.229887 },
          { 361, -58.490339 },
          { 362, -57.752228 },
          { 363, -57.015536 },
          { 364, -56.276483 },
          { 365, -55.542608 },
          { 366, -54.806342 },
          { 367, -54.071645 },
          { 368, -53.338291 },
          { 369, -52.602686 },
          { 370, -51.872455 },
          { 371, -51.139843 },
          { 372, -50.408803 },
          { 373, -49.679313 },
          { 374, -48.951266 },
          { 375, -48.22105 },
          { 376, -47.496203 },
          { 377, -46.769036 },
          { 378, -46.039706 },
          { 379, -45.315831 },
          { 380, -44.593445 },
          { 381, -43.868844 },
          { 382, -43.145856 },
          { 383, -42.424379 },
          { 384, -41.704618 },
          { 385, -40.982549 },
          { 386, -40.265936 },
          { 387, -39.547035 },
          { 388, -38.829769 },
          { 389, -38.114008 },
          { 390, -37.396038 },
          { 391, -36.683458 },
          { 392, -35.9686 },
          { 393, -35.255307 },
          { 394, -34.543521 },
          { 395, -33.829514 },
          { 396, -33.120816 },
          { 397, -32.409758 },
          { 398, -31.700178 },
          { 399, -30.992098 },
          { 400, -30.285499 },
          { 401, -29.580281 },
          { 402, -28.872685 },
          { 403, -28.166521 },
          { 404, -27.461744 },
          { 405, -26.758281 },
          { 406, -26.056217 },
          { 407, -25.355452 },
          { 408, -24.652163 },
          { 409, -23.950178 },
          { 410, -23.253226 },
          { 411, -22.553704 },
          { 412, -21.855386 },
          { 413, -21.158266 },
          { 414, -20.462275 },
          { 415, -19.767403 },
          { 416, -19.069807 },
          { 417, -18.377119 },
          { 418, -17.685398 },
          { 419, -16.990903 },
          { 420, -16.301198 },
          { 421, -15.608575 },
          { 422, -14.916938 },
          { 423, -14.229936 },
          { 424, -13.539951 },
          { 425, -12.850788 },
          { 426, -12.166216 },
          { 427, -11.478531 },
          { 428, -10.79159 },
          { 429, -10.109106 },
          { 430, -9.423427 },
          { 431, -8.738384 },
          { 432, -8.057684 },
          { 433, -7.373686 },
          { 434, -6.693995 },
          { 435, -6.010934 },
          { 436, -5.332089 },
          { 437, -4.649804 },
          { 438, -3.97169 },
          { 439, -3.293848 },
          { 440, -2.616269 },
          { 441, -1.938936 },
          { 442, -1.265573 },
          { 443, -0.588556 },
          { 444, 0.084526 },
          { 445, 0.757552 },
          { 446, 1.430536 },
          { 447, 2.103519 },
          { 448, 2.776535 },
          { 449, 3.449607 },
          { 450, 4.11898 },
          { 451, 4.788509 },
          { 452, 5.458187 },
          { 453, 6.124303 },
          { 454, 6.79446 },
          { 455, 7.461088 },
          { 456, 8.128033 },
          { 457, 8.795301 },
          { 458, 9.459166 },
          { 459, 10.123439 },
          { 460, 10.788156 },
          { 461, 11.453332 },
          { 462, 12.115193 },
          { 463, 12.777613 },
          { 464, 13.440585 },
          { 465, 14.100334 },
          { 466, 14.760704 },
          { 467, 15.421739 },
          { 468, 16.083418 },
          { 469, 16.741991 },
          { 470, 17.401283 },
          { 471, 18.057519 },
          { 472, 18.714547 },
          { 473, 19.372315 },
          { 474, 20.027109 },
          { 475, 20.682773 },
          { 476, 21.33925 },
          { 477, 21.992831 },
          { 478, 22.647292 },
          { 479, 23.298854 },
          { 480, 23.951408 },
          { 481, 24.604838 },
          { 482, 25.25544 },
          { 483, 25.907079 },
          { 484, 26.555822 },
          { 485, 27.205664 },
          { 486, 27.856477 },
          { 487, 28.504503 },
          { 488, 29.15363 },
          { 489, 29.803782 },
          { 490, 30.451176 },
          { 491, 31.095886 },
          { 492, 31.74546 },
          { 493, 32.388544 },
          { 494, 33.036501 },
          { 495, 33.681754 },
          { 496, 34.324373 },
          { 497, 34.968035 },
          { 498, 35.612847 },
          { 499, 36.254974 },
          { 500, 36.898245 },
          { 501, 37.542632 },
          { 502, 38.184274 },
          { 503, 38.823288 },
          { 504, 39.463479 },
          { 505, 40.104745 },
          { 506, 40.74339 },
          { 507, 41.383163 },
          { 508, 42.023955 },
          { 509, 42.662129 },
          { 510, 43.297645 },
          { 511, 43.934228 },
          { 512, 44.572027 },
          { 513, 45.210787 },
          { 514, 45.846954 },
          { 515, 46.48032 },
          { 516, 47.114851 },
          { 517, 47.750451 },
          { 518, 48.387191 },
          { 519, 49.021098 },
          { 520, 49.652208 },
          { 521, 50.284494 },
          { 522, 50.917765 },
          { 523, 51.552005 },
          { 524, 52.183518 },
          { 525, 52.816015 },
          { 526, 53.445691 },
          { 527, 54.076344 },
          { 528, 54.704193 },
          { 529, 55.336802 },
          { 530, 55.962778 },
          { 531, 56.593454 },
          { 532, 57.22118 },
          { 533, 57.849873 },
          { 534, 58.475626 },
          { 535, 59.10226 },
          { 536, 59.729732 },
          { 537, 60.354349 },
          { 538, 60.979736 },
          { 539, 61.605849 },
          { 540, 62.229093 },
          { 541, 62.85309 },
          { 542, 63.477796 },
          { 543, 64.099518 },
          { 544, 64.721973 },
          { 545, 65.345239 },
          { 546, 65.96909 },
          { 547, 66.589831 },
          { 548, 67.211261 },
          { 549, 67.829582 },
          { 550, 68.452286 },
          { 551, 69.071832 },
          { 552, 69.692043 },
          { 553, 70.308887 },
          { 554, 70.926397 },
          { 555, 71.544439 },
          { 556, 72.16301 },
          { 557, 72.781978 },
          { 558, 73.397753 },
          { 559, 74.013922 },
          { 560, 74.626791 },
          { 561, 75.243852 },
          { 562, 75.857451 },
          { 563, 76.471542 },
          { 564, 77.085967 },
          { 565, 77.696923 },
          { 566, 78.30834 },
          { 567, 78.919975 },
          { 568, 79.53193 },
          { 569, 80.144071 },
          { 570, 80.752805 },
          { 571, 81.361739 },
          { 572, 81.970846 },
          { 573, 82.58023 },
          { 574, 83.189752 },
          { 575, 83.795732 },
          { 576, 84.40556 },
          { 577, 85.011789 },
          { 578, 85.618051 },
          { 579, 86.224513 },
          { 580, 86.827166 },
          { 581, 87.433625 },
          { 582, 88.036329 },
          { 583, 88.639128 },
          { 584, 89.241773 },
          { 585, 89.844479 },
          { 586, 90.447108 },
          { 587, 91.045954 },
          { 588, 91.644629 },
          { 589, 92.243107 },
          { 590, 92.841601 },
          { 591, 93.439771 },
          { 592, 94.037945 },
          { 593, 94.635979 },
          { 594, 95.22984 },
          { 595, 95.82369 },
          { 596, 96.417197 },
          { 597, 97.014342 },
          { 598, 97.603451 },
          { 599, 98.196259 },
          { 600, 98.788762 },
          { 601, 99.380849 },
          { 602, 99.968912 },
          { 603, 100.556596 },
          { 604, 101.147669 },
          { 605, 101.73468 },
          { 606, 102.321165 },
          { 607, 102.907315 },
          { 608, 103.492994 },
          { 609, 104.074404 },
          { 610, 104.659289 },
          { 611, 105.239763 },
          { 612, 105.8236 },
          { 613, 106.4031 },
          { 614, 106.982133 },
          { 615, 107.560564 },
          { 616, 108.1385 },
          { 617, 108.715807 },
          { 618, 109.288787 },
          { 619, 109.864881 },
          { 620, 110.4366 },
          { 621, 111.007814 },
          { 622, 111.578289 },
          { 623, 112.148129 },
          { 624, 112.717331 },
          { 625, 113.285869 },
          { 626, 113.849939 },
          { 627, 114.417199 },
          { 628, 114.979841 },
          { 629, 115.541847 },
          { 630, 116.103188 },
          { 631, 116.663767 },
          { 632, 117.223681 },
          { 633, 117.78271 },
          { 634, 118.337225 },
          { 635, 118.891103 },
          { 636, 119.447936 },
          { 637, 120.000115 },
          { 638, 120.54781 },
          { 639, 121.098472 },
          { 640, 121.644514 },
          { 641, 122.193627 },
          { 642, 122.738004 },
          { 643, 123.281607 },
          { 644, 123.820581 },
          { 645, 124.358726 },
          { 646, 124.899902 },
          { 647, 125.432516 },
          { 648, 125.968134 },
          { 649, 126.499017 },
          { 650, 127.029149 },
          { 651, 127.558421 },
          { 652, 128.086818 },
          { 653, 128.610599 },
          { 654, 129.133459 },
          { 655, 129.655451 },
          { 656, 130.172833 },
          { 657, 130.689305 },
          { 658, 131.205004 },
          { 659, 131.715931 },
          { 660, 132.22613 },
          { 661, 132.735396 },
          { 662, 133.239989 },
          { 663, 133.74368 },
          { 664, 134.242785 },
          { 665, 134.741017 },
          { 666, 135.238424 },
          { 667, 135.731102 },
          { 668, 136.223066 },
          { 669, 136.710207 },
          { 670, 137.19283 },
          { 671, 137.678354 },
          { 672, 138.155457 },
          { 673, 138.631683 },
          { 674, 139.107187 },
          { 675, 139.574079 },
          { 676, 140.040338 },
          { 677, 140.50569 },
          { 678, 140.966511 },
          { 679, 141.42263 },
          { 680, 141.878011 },
          { 681, 142.32497 },
          { 682, 142.771194 },
          { 683, 143.216683 },
          { 684, 143.653774 },
          { 685, 144.090136 },
          { 686, 144.521882 },
          { 687, 144.949176 },
          { 688, 145.371887 },
          { 689, 145.790049 },
          { 690, 146.207613 },
          { 691, 146.61677 },
          { 692, 147.0253 },
          { 693, 147.425502 },
          { 694, 147.825096 },
          { 695, 148.216296 },
          { 696, 148.603128 },
          { 697, 148.985517 },
          { 698, 149.363415 },
          { 699, 149.736873 },
          { 700, 150.10212 },
          { 701, 150.462978 },
          { 702, 150.81936 },
          { 703, 151.167556 },
          { 704, 151.511446 },
          { 705, 151.850889 },
          { 706, 152.18215 },
          { 707, 152.50522 },
          { 708, 152.824014 },
          { 709, 153.138503 },
          { 710, 153.444792 },
          { 711, 153.742957 },
          { 712, 154.033025 },
          { 713, 154.318837 },
          { 714, 154.59653 },
          { 715, 154.866134 },
          { 716, 155.12765 },
          { 717, 155.381112 },
          { 718, 155.630385 },
          { 719, 155.867729 },
          { 720, 156.097089 },
          { 721, 156.3184 },
          { 722, 156.531676 },
          { 723, 156.736951 },
          { 724, 156.930402 },
          { 725, 157.115905 },
          { 726, 157.293381 },
          { 727, 157.459105 },
          { 728, 157.616836 },
          { 729, 157.762785 },
          { 730, 157.900741 },
          { 731, 158.023128 },
          { 732, 158.137639 },
          { 733, 158.2442 },
          { 734, 158.335178 },
          { 735, 158.418238 },
          { 736, 158.489597 },
          { 737, 158.549219 },
          { 738, 158.593311 },
          { 739, 158.629599 },
          { 740, 158.654129 },
          { 741, 158.666977 },
          { 742, 158.668171 },
          { 743, 158.653837 },
          { 744, 158.631718 },
          { 745, 158.594052 },
          { 746, 158.544741 },
          { 747, 158.479981 },
          { 748, 158.40742 },
          { 749, 158.319346 },
          { 750, 158.219636 },
          { 751, 158.1083 },
          { 752, 157.981495 },
          { 753, 157.843074 },
          { 754, 157.693002 },
          { 755, 157.527506 },
          { 756, 157.354167 },
          { 757, 157.16538 },
          { 758, 156.964941 },
          { 759, 156.749054 },
          { 760, 156.525312 },
          { 761, 156.289954 },
          { 762, 156.04298 },
          { 763, 155.788134 },
          { 764, 155.521636 },
          { 765, 155.243474 },
          { 766, 154.957508 },
          { 767, 154.663646 },
          { 768, 154.358171 },
          { 769, 154.048691 },
          { 770, 153.731307 },
          { 771, 153.406107 },
          { 772, 153.07689 },
          { 773, 152.743608 },
          { 774, 152.402499 },
          { 775, 152.061151 },
          { 776, 151.715776 },
          { 777, 151.366353 },
          { 778, 151.016782 },
          { 779, 150.666959 }
      });
    }

    @Test
    public void testMarsThetaChecker() {
        double normalizedExpectedMarsTheta = Math.toDegrees(MathUtils.normalizeAngle(Math.toRadians(expectedMarsTheta), 0.0));
        double normalizedKeplerianMarsTheta = Math.toDegrees(MathUtils.normalizeAngle(keplerianPredictor.getMarsTheta(timeId), 0.0));

        assertTrue(Math.abs(normalizedExpectedMarsTheta - normalizedKeplerianMarsTheta) <= ERROR_TOLERANCE_DEGREES
                   || Math.abs(normalizedExpectedMarsTheta + normalizedKeplerianMarsTheta) <= ERROR_TOLERANCE_DEGREES);
    }
}
