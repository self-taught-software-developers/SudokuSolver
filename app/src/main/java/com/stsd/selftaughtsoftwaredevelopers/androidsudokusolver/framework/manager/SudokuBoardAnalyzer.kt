package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class SudokuBoardAnalyzer : ImageAnalysis.Analyzer {

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.Builder().build())

    @ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {

        imageProxy.image?.let { mediaImage ->
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            recognizer.process(image)
                .addOnCompleteListener { result ->

                    if (result.isSuccessful) {

                        //TODO Based on the image's bounding box place a text on the screen.
                        //TODO convert a boundingBox to an xy tile placement.
//                        result.result
//                            .textBlocks.flatMap {
//                                it.lines.flatMap { it.elements }
//                            }.forEach { element ->
//                                val elementText = element.text
//                                val elementFrame = element.boundingBox
//                                println("$elementText, $elementFrame")
//                            }

                    }

                    imageProxy.close()
                }

        }
    }

//    val barcodeInCenter = task.result.firstOrNull { barcode ->
//
//        val barcodeBoundingBox = barcode?.boundingBox?.toRectF()
//            ?: return@firstOrNull false
//
//        "${overlayBoundingBox}, $barcodeBoundingBox".coreDataLogIt("BarcodeAnalyzer")
//
//        overlayBoundingBox.intersect(barcodeBoundingBox)
//
//    }

}